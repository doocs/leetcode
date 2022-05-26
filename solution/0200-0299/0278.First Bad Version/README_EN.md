# [278. First Bad Version](https://leetcode.com/problems/first-bad-version)

[中文文档](/solution/0200-0299/0278.First%20Bad%20Version/README.md)

## Description

<p>You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.</p>

<p>Suppose you have <code>n</code> versions <code>[1, 2, ..., n]</code> and you want to find out the first bad one, which causes all the following ones to be bad.</p>

<p>You are given an API <code>bool isBadVersion(version)</code> which will return whether <code>version</code> is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.</p>

<p><b>Example:</b></p>

<pre>

Given n = 5, and version = 4 is the first bad version.



<code>call isBadVersion(3) -&gt; false

call isBadVersion(5)&nbsp;-&gt; true

call isBadVersion(4)&nbsp;-&gt; true



Then 4 is the first bad version.&nbsp;</code>

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# The isBadVersion API is already defined for you.
# @param version, an integer
# @return an integer
# def isBadVersion(version):

class Solution:
    def firstBadVersion(self, n):
        """
        :type n: int
        :rtype: int
        """
        low, high = 1, n
        while low < high:
            mid = low + ((high - low) >> 1)
            if isBadVersion(mid):
                high = mid
            else:
                low = mid + 1
        return low
```

### **Java**

```java
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low = 1, high = n;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (isBadVersion(mid)) high = mid;
            else low = mid + 1;
        }
        return low;
    }
}
```

### **JavaScript**

```js
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
var solution = function (isBadVersion) {
  /**
   * @param {integer} n Total versions
   * @return {integer} The first bad version
   */
  return function (n) {
    let low = 1,
      high = n;
    while (low < high) {
      const mid = low + ((high - low) >> 1);
      if (isBadVersion(mid)) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return low;
  };
};
```

### **...**

```

```

<!-- tabs:end -->
