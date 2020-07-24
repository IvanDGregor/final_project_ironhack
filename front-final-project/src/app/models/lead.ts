import { InformationContact } from './informationContact';

export interface Lead {
    id: number;
    informationContact: InformationContact;
    salesRepId: number;
    company: string;
}