import { InformationContact } from './informationContact';
import { Status } from './status';

export interface CreditCard {
    id: string;
    pin: string;
    userId: string;
    status: Status;
}