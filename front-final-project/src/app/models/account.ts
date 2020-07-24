import { IndustryType } from './industryType';
import { Status } from './status';

export interface Account {
    id: string;
    balance: number;
    status: Status;
    secretKey: string;
    userId: string;
}
