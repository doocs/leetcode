# [2433. Find The Original Array of Prefix Xor](https://leetcode.com/problems/find-the-original-array-of-prefix-xor)

[中文文档](/solution/2400-2499/2433.Find%20The%20Original%20Array%20of%20Prefix%20Xor/README.md)

<!-- tags:Bit Manipulation,Array -->

<!-- difficulty:Medium -->

## Description

<p>You are given an <strong>integer</strong> array <code>pref</code> of size <code>n</code>. Find and return <em>the array </em><code>arr</code><em> of size </em><code>n</code><em> that satisfies</em>:</p>

<ul>
	<li><code>pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i]</code>.</li>
</ul>

<p>Note that <code>^</code> denotes the <strong>bitwise-xor</strong> operation.</p>

<p>It can be proven that the answer is <strong>unique</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> pref = [5,2,0,3,1]
<strong>Output:</strong> [5,7,2,3,2]
<strong>Explanation:</strong> From the array [5,7,2,3,2] we have the following:
- pref[0] = 5.
- pref[1] = 5 ^ 7 = 2.
- pref[2] = 5 ^ 7 ^ 2 = 0.
- pref[3] = 5 ^ 7 ^ 2 ^ 3 = 3.
- pref[4] = 5 ^ 7 ^ 2 ^ 3 ^ 2 = 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> pref = [13]
<strong>Output:</strong> [13]
<strong>Explanation:</strong> We have pref[0] = arr[0] = 13.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pref.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= pref[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1: Bit Manipulation

According to the problem statement, we have equation one:

$$
pref[i]=arr[0] \oplus arr[1] \oplus \cdots \oplus arr[i]
$$

So, we also have equation two:

$$
pref[i-1]=arr[0] \oplus arr[1] \oplus \cdots \oplus arr[i-1]
$$

We perform a bitwise XOR operation on equations one and two, and get:

$$
pref[i] \oplus pref[i-1]=arr[i]
$$

That is, each item in the answer array is obtained by performing a bitwise XOR operation on the adjacent two items in the prefix XOR array.

The time complexity is $O(n)$, where $n$ is the length of the prefix XOR array. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def findArray(self, pref: List[int]) -> List[int]:
        return [a ^ b for a, b in pairwise([0] + pref)]
```

```java
class Solution {
    public int[] findArray(int[] pref) {
        int n = pref.length;
        int[] ans = new int[n];
        ans[0] = pref[0];
        for (int i = 1; i < n; ++i) {
            ans[i] = pref[i - 1] ^ pref[i];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findArray(vector<int>& pref) {
        int n = pref.size();
        vector<int> ans = {pref[0]};
        for (int i = 1; i < n; ++i) {
            ans.push_back(pref[i - 1] ^ pref[i]);
        }
        return ans;
    }
};
```

```go
func findArray(pref []int) []int {
	n := len(pref)
	ans := []int{pref[0]}
	for i := 1; i < n; i++ {
		ans = append(ans, pref[i-1]^pref[i])
	}
	return ans
}
```

```ts
function findArray(pref: number[]): number[] {
    let ans = pref.slice();
    for (let i = 1; i < pref.length; i++) {
        ans[i] = pref[i - 1] ^ pref[i];
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn find_array(pref: Vec<i32>) -> Vec<i32> {
        let n = pref.len();
        let mut res = vec![0; n];
        res[0] = pref[0];
        for i in 1..n {
            res[i] = pref[i] ^ pref[i - 1];
        }
        res
    }
}
```

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* findArray(int* pref, int prefSize, int* returnSize) {
    int* res = (int*) malloc(sizeof(int) * prefSize);
    res[0] = pref[0];
    for (int i = 1; i < prefSize; i++) {
        res[i] = pref[i - 1] ^ pref[i];
    }
    *returnSize = prefSize;
    return res;
}
```

<!-- tabs:end -->

<!-- end -->
