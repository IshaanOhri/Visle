import mongoose from 'mongoose';

const ChannelSchema = new mongoose.Schema({
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
	participants: [
		{
			id: {
				type: String,
				required: true
			},
			name: {
				type: String,
				required: true
			},
			image: {
				type: String,
				required: true
			}
		}
	],
	createdAt: {
		type: Date,
		default: Date.now
	}
});

const Channel = mongoose.model('Channel', ChannelSchema);

export default Channel;
