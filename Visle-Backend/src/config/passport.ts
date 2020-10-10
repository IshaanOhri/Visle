import GoogleStrategy from 'passport-google-oauth20';
import logger from '../logger/config';
import Participant from '../modals/Participant';

module.exports = (passport: any) => {
	passport.use(
		new GoogleStrategy.Strategy(
			{
				clientID: String(process.env.GOOGLE_CLIENT_ID),
				clientSecret: String(process.env.GOOGLE_CLIENT_SECRET),
				callbackURL: String(process.env.GOOGLE_CALLBACK)
			},
			async (accessToken, refreshToken, profile, done) => {
				const newUser = {
					participantID: profile.id,
					displayName: profile.displayName,
					firstName: profile.name?.givenName,
					lastName: profile.name?.familyName,
					image: profile.photos![0].value
				};

				try {
					let participant = await Participant.findOne({ participantID: profile.id });

					if (participant) {
						done(undefined, participant);
					} else {
						participant = await Participant.create(newUser);
						done(undefined, participant);
					}
				} catch (err) {
					logger.error(err);
				}
			}
		)
	);
	passport.serializeUser((user: any, done: any) => {
		done(null, user.id);
	});

	passport.deserializeUser((id: any, done: any) => {
		Participant.findById(id, (err, user) => {
			done(err, user);
		});
	});
};
