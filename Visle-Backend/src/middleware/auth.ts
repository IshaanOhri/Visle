/* eslint-disable consistent-return */
import { NextFunction, Request, Response } from 'express';

const ensureAuth = async (req: Request, res: Response, next: NextFunction) => {
	if (req.isAuthenticated()) {
		return next();
	}

	res.redirect('/');
};

const ensureGuest = async (req: Request, res: Response, next: NextFunction) => {
	if (req.isAuthenticated()) {
		res.redirect('/dashboard');
	} else {
		return next();
	}
};

export { ensureAuth, ensureGuest };
