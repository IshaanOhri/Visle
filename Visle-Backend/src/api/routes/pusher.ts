import { Router } from 'express';
import { pusherAuth } from '../controllers/pusher';

const pusherRouter: Router = Router();

pusherRouter.post('/auth', pusherAuth);

export default pusherRouter;
