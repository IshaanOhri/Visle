/* eslint-disable import/first */
require('newrelic');

import express, { Application, Request, Response } from 'express';
import cors from 'cors';
import morgan from 'morgan';
import path from 'path';
import passport from 'passport';
import handlebars from 'express-handlebars';
import mongoose from 'mongoose';
import session from 'express-session';
import logger from './logger/config';
import healthRouter from './api/routes/health';
import databaseConnect from './database/database';
import channelRouter from './api/routes/channel';
import pusherRouter from './api/routes/pusher';
import authRouter from './api/routes/auth';
import loginRouter from './api/routes/login';

const app: Application = express();

// CORS
app.use(cors());

// Serve static files
app.use(express.static(path.join(__dirname, '/public')));

// Body parser
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// PORT and HOST initialization
const PORT: number = Number(process.env.PORT);
const HOST: string = String(process.env.HOST);

// Initialize passport
require('./config/passport')(passport);

// Initialize MongoStore
const MongoStore = require('connect-mongo')(session);

// Connect to database
databaseConnect();

// Morgan configuration
app.use(
	morgan((tokens, req: Request, res: Response) => {
		logger.info(
			`Method: ${tokens.method(req, res)} URL: ${tokens.url(req, res)} Status: ${tokens.status(req, res)} Resp Time: ${tokens['response-time'](
				req,
				res
			)} ms`
		);
		return null;
	})
);

// Setup handlebars
app.engine('.hbs', handlebars({ defaultLayout: 'main', extname: '.hbs' }));
app.set('view engine', '.hbs');
app.set('views', path.join(__dirname, 'views'));

// Express sessions
app.use(
	session({
		secret: 'keyboard cat',
		resave: false,
		saveUninitialized: false,
		store: new MongoStore({ mongooseConnection: mongoose.connection })
		// cookie:{secure:true} Works only with HTTPS
	})
);

// Passport middleware
app.use(passport.initialize());
app.use(passport.session());

// Static Folder
app.use(express.static(path.join(__dirname, '/public')));

// Import routers
app.use(healthRouter);
app.use('/channel', channelRouter);
app.use('/pusher', pusherRouter);
app.use(loginRouter);
app.use('/auth', authRouter);

// Default route
app.use('*', (req: Request, res: Response) => {
	res.redirect('/');
});

app.listen(PORT, HOST, () => {
	logger.info(`Visle server listening on http://${HOST}:${PORT}`);
});
