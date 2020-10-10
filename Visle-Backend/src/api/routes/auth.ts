import { Request, Response, Router } from 'express';
import passport from 'passport';
import { googleCallback, googleLogout } from '../controllers/auth';

const authRouter: Router = Router();

// @desc	Auth with Google
// @route	GET /auth/google
authRouter.get('/google', passport.authenticate('google', { scope: ['profile'] }));

// @desc	Google auth callback
// @route	GET /auth/google/callback
authRouter.get('/google/callback', passport.authenticate('google', { failureRedirect: '/' }), googleCallback);

// @desc Logout user
// @route GET /auth/logout
authRouter.get('/logout', googleLogout);

export default authRouter;
