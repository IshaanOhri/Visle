import mongoose from 'mongoose';

const ParticipantSchema = new mongoose.Schema({
	participantID: {
		type: String,
		required: true
	},
	displayName: {
		type: String,
		required: true
	},
	firstName: {
		type: String,
		required: true
	},
	lastName: {
		type: String,
		required: true
	},
	image: {
		type: String
	},
	channelID: {
		type: mongoose.Schema.Types.ObjectId,
		ref: 'Channel'
	},
	joinedAt: {
		type: Date,
		default: Date.now
	}
});

const Participant = mongoose.model('Participant', ParticipantSchema);

export default Participant;
