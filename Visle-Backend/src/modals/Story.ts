import mongoose from 'mongoose';

const StorySchema = new mongoose.Schema({
	channelID: {
		type: String,
		unique: true,
		required: true,
		trim: true
	},
	channelName: {
		type: String,
		required: true,
		trim: true
	},
	instructorName: {
		type: String,
		required: true,
		trim: true
	},
	story: [
		{
			query: {
				type: String,
				required: true,
				trim: true
			},
			urls: [
				{
					type: String,
					required: true,
					trim: true
				}
			],
			createdAt: {
				type: Date,
				default: Date.now
			}
		}
	]
});

const Story = mongoose.model('Story', StorySchema);

export default Story;
