// @ts-ignore
import client from './connect-client.default';
import Account from './com/vaadin/connect/plugin/generator/services/modelpackage/subpackage/Account';

export function getSubAccountPackage(
  name: string
): Promise<Account> {
  return client.call('SubModelPackageService', 'getSubAccountPackage', {name});
}
