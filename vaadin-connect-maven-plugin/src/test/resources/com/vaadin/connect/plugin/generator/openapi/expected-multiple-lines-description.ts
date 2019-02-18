/**
 * This class is used
 * <h1>for OpenApi generator test</h1>
 *
 * This module has been generated from GeneratorTestClass.java
 * @module GeneratorTestClass
 */

// @ts-ignore
import client from './connect-client.default';

/**
 * Get all users
 *
 * Return list of users
 */
export function getAllUsers(): Promise<any[]> {
  return client.call('GeneratorTestClass', 'getAllUsers', undefined, {requireCredentials: false});
}