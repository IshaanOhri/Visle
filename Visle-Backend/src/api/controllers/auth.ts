import { Request, Response } from 'express';
import logger from '../../logger/config';

const googleCallback = async (req: Request, res: Response) => {
	res.redirect('/dashboard');
};

const googleLogout = async (req: Request, res: Response) => {
	req.logout();
	res.redirect('/');
};

export { googleCallback, googleLogout };
