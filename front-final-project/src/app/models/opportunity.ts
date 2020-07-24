import { ProductType } from './productType';
import { OpportunityStatus } from './opportunityStatus';
import { OpportunityListComponent } from '../opportunity-list/opportunity-list.component';

export interface Opportunity {
  id: number;
  product: ProductType;
  opportunityStatus: OpportunityStatus;
  quantity: number;
  salesRepId: number;
  newStatus: OpportunityStatus;
}
