# [2601. Prime Subtraction Operation](https://leetcode.com/problems/prime-subtraction-operation)

[中文文档](/solution/2600-2699/2601.Prime%20Subtraction%20Operation/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of length <code>n</code>.</p>

<p>You can perform the following operation as many times as you want:</p>

<ul>
	<li>Pick an index <code>i</code> that you haven&rsquo;t picked before, and pick a prime <code>p</code> <strong>strictly less than</strong> <code>nums[i]</code>, then subtract <code>p</code> from <code>nums[i]</code>.</li>
</ul>

<p>Return <em>true if you can make <code>nums</code> a strictly increasing array using the above operation and false otherwise.</em></p>

<p>A <strong>strictly increasing array</strong> is an array whose each element is strictly greater than its preceding element.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,9,6,10]
<strong>Output:</strong> true
<strong>Explanation:</strong> In the first operation: Pick i = 0 and p = 3, and then subtract 3 from nums[0], so that nums becomes [1,9,6,10].
In the second operation: i = 1, p = 7, subtract 7 from nums[1], so nums becomes equal to [1,2,6,10].
After the second operation, nums is sorted in strictly increasing order, so the answer is true.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,8,11,12]
<strong>Output:</strong> true
<strong>Explanation: </strong>Initially nums is sorted in strictly increasing order, so we don&#39;t need to make any operations.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,8,3]
<strong>Output:</strong> false
<strong>Explanation:</strong> It can be proven that there is no way to perform operations to make nums sorted in strictly increasing order, so the answer is false.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code><font face="monospace">nums.length == n</font></code></li>
</ul>

## Solutions

**Solution 1: Preprocessing prime numbers + binary search**

We first preprocess all the primes within $1000$ and record them in the array $p$.

For each element $nums[i]$ in the array $nums$, we need to find a prime $p[j]$ such that $p[j] \gt nums[i] - nums[i + 1]$ and $p[j]$ is as small as possible. If there is no such prime, it means that it cannot be strictly increased by subtraction operations, return `false`. If there is such a prime, we will subtract $p[j]$ from $nums[i]$ and continue to process the next element.

If all the elements in $nums$ are processed, it means that it can be strictly increased by subtraction operations, return `true`.

The time complexity is $O(n \log n)$ and the space complexity is $O(n)$. where $n$ is the length of the array $nums$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def primeSubOperation(self, nums: List[int]) -> bool:
        p = []
        for i in range(2, max(nums)):
            for j in p:
                if i % j == 0:
                    break
            else:
                p.append(i)

        n = len(nums)
        for i in range(n - 2, -1, -1):
            if nums[i] < nums[i + 1]:
                continue
            j = bisect_right(p, nums[i] - nums[i + 1])
            if j == len(p) or p[j] >= nums[i]:
                return False
            nums[i] -= p[j]
        return True
```

### **Java**

```java
class Solution {
    public boolean primeSubOperation(int[] nums) {
        List<Integer> p = new ArrayList<>();
        for (int i = 2; i <= 1000; ++i) {
            boolean ok = true;
            for (int j : p) {
                if (i % j == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                p.add(i);
            }
        }
        int n = nums.length;
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                continue;
            }
            int j = search(p, nums[i] - nums[i + 1]);
            if (j == p.size() || p.get(j) >= nums[i]) {
                return false;
            }
            nums[i] -= p.get(j);
        }
        return true;
    }

    private int search(List<Integer> nums, int x) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums.get(mid) > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool primeSubOperation(vector<int>& nums) {
        vector<int> p;
        for (int i = 2; i <= 1000; ++i) {
            bool ok = true;
            for (int j : p) {
                if (i % j == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                p.push_back(i);
            }
        }
        int n = nums.size();
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                continue;
            }
            int j = upper_bound(p.begin(), p.end(), nums[i] - nums[i + 1]) - p.begin();
            if (j == p.size() || p[j] >= nums[i]) {
                return false;
            }
            nums[i] -= p[j];
        }
        return true;
    }
};
```

### **Go**

```go
func primeSubOperation(nums []int) bool {
	p := []int{}
	for i := 2; i <= 1000; i++ {
		ok := true
		for _, j := range p {
			if i%j == 0 {
				ok = false
				break
			}
		}
		if ok {
			p = append(p, i)
		}
	}
	for i := len(nums) - 2; i >= 0; i-- {
		if nums[i] < nums[i+1] {
			continue
		}
		j := sort.SearchInts(p, nums[i]-nums[i+1]+1)
		if j == len(p) || p[j] >= nums[i] {
			return false
		}
		nums[i] -= p[j]
	}
	return true
}
```

### **TypeScript**

```ts
function primeSubOperation(nums: number[]): boolean {
    const p: number[] = [];
    for (let i = 2; i <= 1000; ++i) {
        let ok = true;
        for (const j of p) {
            if (i % j === 0) {
                ok = false;
                break;
            }
        }
        if (ok) {
            p.push(i);
        }
    }
    const search = (x: number): number => {
        let l = 0;
        let r = p.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (p[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const n = nums.length;
    for (let i = n - 2; i >= 0; --i) {
        if (nums[i] < nums[i + 1]) {
            continue;
        }
        const j = search(nums[i] - nums[i + 1]);
        if (j === p.length || p[j] >= nums[i]) {
            return false;
        }
        nums[i] -= p[j];
    }
    return true;
}
```

### **...**

```

```

<!-- tabs:end -->
