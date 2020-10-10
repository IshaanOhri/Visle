import { Router } from 'express';
import { ensureAuth, ensureGuest } from '../../middleware/auth';
import { dashboard, login } from '../controllers/login';

const loginRouter: Router = Router();

// @desc	Login/Landing page
// @route	GET /
loginRouter.get('/', ensureGuest, login);

// @desc	Dashboard
// @route	GET /dashboard
loginRouter.get('/dashboard', ensureAuth, dashboard);

export default loginRouter;
