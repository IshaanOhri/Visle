import { JoinedParticipant } from './JoinedParticipant';

export interface ChannelInterface {
	channelID: string;
	channelName: string;
	instructorName: string;
	participants: JoinedParticipant[];
}
