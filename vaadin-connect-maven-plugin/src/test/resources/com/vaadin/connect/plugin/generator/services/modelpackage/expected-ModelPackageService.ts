// @ts-ignore
import client from './connect-client.default';
import Account from './com/vaadin/connect/plugin/generator/services/modelpackage/ModelPackageService/Account';

/**
 * Get a list of user name.
 *
 * Return list of user name
 */
export function getListOfUserName(): Promise<string[]> {
  return client.call('ModelPackageService', 'getListOfUserName');
}

/**
 * Get a collection by author name. The generator should not mix this type with the Java's Collection type.
 *
 * @param name author name
 * Return a collection
 */
export function getSameModelPackage(
  name: string
): Promise<Account> {
  return client.call('ModelPackageService', 'getSameModelPackage', {name});
}
