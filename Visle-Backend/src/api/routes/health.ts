import { Router } from 'express';
import { health } from '../controllers/health';

const healthRouter: Router = Router();

// @desc	Health Route
// @route	GET /health
healthRouter.get('/health', health);

export default healthRouter;
