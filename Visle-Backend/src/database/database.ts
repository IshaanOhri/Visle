import { connect } from 'mongoose';
import logger from '../logger/config';

const databaseConnect = async () => {
	try {
		const connection = await connect(String(process.env.MONGO_URI), {
			useNewUrlParser: true,
			useUnifiedTopology: true,
			useFindAndModify: false
		});

		logger.info('Connection to database successful');
	} catch (err) {
		logger.error('Connection to database unsuccessful');
		logger.error(err);
		process.exit(1);
	}
};

export default databaseConnect;
