import { Router } from 'express';
import { createChannel, reciteStory, joinChannel, leaveChannel, add, remove, participants } from '../controllers/channel';

const channelRouter: Router = Router();

// @desc	Create channel
// @route	POST /channel/create
channelRouter.post('/create', createChannel);

// @desc	Recite story
// @route	POST /channel/recite
channelRouter.post('/recite', reciteStory);

// @desc	Join channel
// @route	GET /channel/join?channelID=&participantID=
channelRouter.get('/join', joinChannel);

// @desc	Leave channel
// @route	GET /channel/leave?channelID=&participantID=
channelRouter.get('/leave', leaveChannel);

// @desc	Add user to DB
// @route	GET /channel/add?channelID=&participantID=
channelRouter.get('/add', add);

// @desc	Remove user from DB
// @route	GET /channel/remove?channelID=&participantID=
channelRouter.get('/remove', remove);

// @desc	Participant list
// @route	GET /channel/participants?channelID=
channelRouter.get('/participants', participants);

export default channelRouter;
