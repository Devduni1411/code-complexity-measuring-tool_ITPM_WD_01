export interface User {
  id: number;
  username: string;
  password: string;
  active: boolean;
  roles: string;
  permissions: string[];
  roleList: string[];
  permissionList: string[];
}
