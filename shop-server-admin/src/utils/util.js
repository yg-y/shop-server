/**
 * Created by Young on 2019/7/19
 */
export function deepClone(data) {
  return JSON.parse(JSON.stringify(data))
}
