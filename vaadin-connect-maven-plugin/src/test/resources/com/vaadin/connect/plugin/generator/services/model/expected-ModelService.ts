// @ts-ignore
import client from './connect-client.default';
import Account from './com/vaadin/connect/plugin/generator/services/model/ModelService/Account';
import Group from './com/vaadin/connect/plugin/generator/services/model/ModelService/Group';
import ModelFromDifferentPackage from './com/vaadin/connect/plugin/generator/services/model/subpackage/ModelFromDifferentPackage';

export function getAccountByGroups(
  groups: Group[]
): Promise<Account> {
  return client.call('ModelService', 'getAccountByGroups', {groups});
}

/**
 * Get account by username.
 *
 * @param userName username of the account
 * Return the account with given userName
 */
export function getAccountByUserName(
  userName: string
): Promise<Account> {
  return client.call('ModelService', 'getAccountByUserName', {userName});
}

export function getArrayOfAccount(): Promise<Account[]> {
  return client.call('ModelService', 'getArrayOfAccount');
}

export function getMapGroups(): Promise<Map<string, Group>> {
  return client.call('ModelService', 'getMapGroups');
}

/**
 * The import path of this model should be correct.
 *
 *
 */
export function getModelFromDifferentPackage(): Promise<ModelFromDifferentPackage> {
  return client.call('ModelService', 'getModelFromDifferentPackage');
}

export function makeSureUnhandledJavaTypeWorks(): Promise<any> {
  return client.call('ModelService', 'makeSureUnhandledJavaTypeWorks');
}
