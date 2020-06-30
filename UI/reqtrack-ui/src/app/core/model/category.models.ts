import { Department } from './department.models';
import { Users } from './user.models';

export class Category{
    id?:any;
    name:string;
    description:string;
    departmentDto?:Department;
}