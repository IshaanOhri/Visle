import { Request, Response } from 'express';
import { code, message } from '../../config/messages';

const health = async (req: Request, res: Response) => {
	res.send({
		success: true,
		code: code.homeRoute,
		message: message.homeRoute
	});
};

export { health };
