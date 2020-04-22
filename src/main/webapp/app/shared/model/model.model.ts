export interface IModel {
  id?: string;
  lastName?: string;
  firstName?: string;
  patronymic?: string;
  height?: string;
  chest?: string;
  waist?: string;
  hips?: string;
}

export class Model implements IModel {
  constructor(
    public id?: string,
    public lastName?: string,
    public firstName?: string,
    public patronymic?: string,
    public height?: string,
    public chest?: string,
    public waist?: string,
    public hips?: string
  ) {}
}
