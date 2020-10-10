import { StorySnippet } from './Story-Snippet';

export interface StoryInterface {
	channelID: string;
	channelName: string;
	instructorName: string;
	story: StorySnippet[];
}
