/**
 * Definition for isBadVersion()
 * 
 * @param {integer} version number
 * @return {boolean} whether the version is bad
 * isBadVersion = function(version) {
 *     ...
 * };
 */

/**
 * @param {function} isBadVersion()
 * @return {function}
 */
const solution = function(isBadVersion) {
  /**
   * @param {integer} n Total versions
   * @return {integer} The first bad version
   */
  return function(n) {
      if(n === 1) return n;
      let left = 1, right = n;
      while(left < right){
        let mid = left + Math.floor((right-left)/2);
        if(isBadVersion(mid)){
          if(mid === left) return mid;
          else right = mid;
        }else{
          if(isBadVersion(mid+1)) return mid+1;
          else left = mid + 1;
        }
      }
  };
};