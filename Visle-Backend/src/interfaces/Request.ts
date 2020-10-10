import { Participant } from './Participant';

declare namespace Express {
	export interface Request {
		participant?: Participant;
	}
}
