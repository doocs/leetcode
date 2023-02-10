# [2554. Maximum Number of Integers to Choose From a Range I](https://leetcode.com/problems/maximum-number-of-integers-to-choose-from-a-range-i)

[中文文档](/solution/2500-2599/2554.Maximum%20Number%20of%20Integers%20to%20Choose%20From%20a%20Range%20I/README.md)

## Description

<p>You are given an integer array <code>banned</code> and two integers <code>n</code> and <code>maxSum</code>. You are choosing some number of integers following the below rules:</p>

<ul>
	<li>The chosen integers have to be in the range <code>[1, n]</code>.</li>
	<li>Each integer can be chosen <strong>at most once</strong>.</li>
	<li>The chosen integers should not be in the array <code>banned</code>.</li>
	<li>The sum of the chosen integers should not exceed <code>maxSum</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of integers you can choose following the mentioned rules</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> banned = [1,6,5], n = 5, maxSum = 6
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can choose the integers 2 and 4.
2 and 4 are from the range [1, 5], both did not appear in banned, and their sum is 6, which did not exceed maxSum.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> banned = [1,2,3,4,5,6,7], n = 8, maxSum = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> You cannot choose any integer while following the mentioned conditions.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> banned = [11], n = 7, maxSum = 50
<strong>Output:</strong> 7
<strong>Explanation:</strong> You can choose the integers 1, 2, 3, 4, 5, 6, and 7.
They are from the range [1, 7], all did not appear in banned, and their sum is 28, which did not exceed maxSum.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= banned.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= banned[i], n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= maxSum &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxCount(self, banned: List[int], n: int, maxSum: int) -> int:
        ans = s = 0
        ban = set(banned)
        for i in range(1, n + 1):
            if s + i > maxSum:
                break
            if i not in ban:
                ans += 1
                s += i
        return ans
```

```python
class Solution:
    def maxCount(self, banned: List[int], n: int, maxSum: int) -> int:
        banned.extend([0, n + 1])
        ban = sorted(x for x in set(banned) if x < n + 2)
        ans = 0
        for i, j in pairwise(ban):
            left, right = 0, j - i - 1
            while left < right:
                mid = (left + right + 1) >> 1
                if (i + 1 + i + mid) * mid // 2 <= maxSum:
                    left = mid
                else:
                    right = mid - 1
            ans += left
            maxSum -= (i + 1 + i + left) * left // 2
            if maxSum <= 0:
                break
        return ans
```

### **Java**

```java
class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> ban = new HashSet<>(banned.length);
        for (int x : banned) {
            ban.add(x);
        }
        int ans = 0, s = 0;
        for (int i = 1; i <= n && s + i <= maxSum; ++i) {
            if (!ban.contains(i)) {
                ++ans;
                s += i;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> black = new HashSet<>();
        black.add(0);
        black.add(n + 1);
        for (int x : banned) {
            if (x < n + 2) {
                black.add(x);
            }
        }
        List<Integer> ban = new ArrayList<>(black);
        Collections.sort(ban);
        int ans = 0;
        for (int k = 1; k < ban.size(); ++k) {
            int i = ban.get(k - 1), j = ban.get(k);
            int left = 0, right = j - i - 1;
            while (left < right) {
                int mid = (left + right + 1) >>> 1;
                if ((i + 1 + i + mid) * 1L * mid / 2 <= maxSum) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            ans += left;
            maxSum -= (i + 1 + i + left) * 1L * left / 2;
            if (maxSum <= 0) {
                break;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxCount(vector<int>& banned, int n, int maxSum) {
        unordered_set<int> ban(banned.begin(), banned.end());
        int ans = 0, s = 0;
        for (int i = 1; i <= n && s + i <= maxSum; ++i) {
            if (!ban.count(i)) {
                ++ans;
                s += i;
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxCount(vector<int>& banned, int n, int maxSum) {
        banned.push_back(0);
        banned.push_back(n + 1);
        sort(banned.begin(), banned.end());
        banned.erase(unique(banned.begin(), banned.end()), banned.end());
        banned.erase(remove_if(banned.begin(), banned.end(), [&](int x) { return x > n + 1; }), banned.end());
        int ans = 0;
        for (int k = 1; k < banned.size(); ++k) {
            int i = banned[k - 1], j = banned[k];
            int left = 0, right = j - i - 1;
            while (left < right) {
                int mid = left + ((right - left + 1) / 2);
                if ((i + 1 + i + mid) * 1LL * mid / 2 <= maxSum) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            ans += left;
            maxSum -= (i + 1 + i + left) * 1LL * left / 2;
            if (maxSum <= 0) {
                break;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxCount(banned []int, n int, maxSum int) (ans int) {
	ban := map[int]bool{}
	for _, x := range banned {
		ban[x] = true
	}
	s := 0
	for i := 1; i <= n && s+i <= maxSum; i++ {
		if !ban[i] {
			ans++
			s += i
		}
	}
	return
}
```

```go
func maxCount(banned []int, n int, maxSum int) (ans int) {
	banned = append(banned, []int{0, n + 1}...)
	sort.Ints(banned)
	ban := []int{}
	for i, x := range banned {
		if (i > 0 && x == banned[i-1]) || x > n+1 {
			continue
		}
		ban = append(ban, x)
	}
	for k := 1; k < len(ban); k++ {
		i, j := ban[k-1], ban[k]
		left, right := 0, j-i-1
		for left < right {
			mid := (left + right + 1) >> 1
			if (i+1+i+mid)*mid/2 <= maxSum {
				left = mid
			} else {
				right = mid - 1
			}
		}
		ans += left
		maxSum -= (i + 1 + i + left) * left / 2
		if maxSum <= 0 {
			break
		}
	}
	return
}
```

### **TypeScript**

```ts
function maxCount(banned: number[], n: number, maxSum: number): number {
    const set = new Set(banned);
    let sum = 0;
    let ans = 0;
    for (let i = 1; i <= n; i++) {
        if (i + sum > maxSum) {
            break;
        }
        if (set.has(i)) {
            continue;
        }
        sum += i;
        ans++;
    }
    return ans;
}
```

### **Rust**

```rust
use std::collections::HashSet;
impl Solution {
    pub fn max_count(banned: Vec<i32>, n: i32, max_sum: i32) -> i32 {
        let mut set = banned.into_iter().collect::<HashSet<i32>>();
        let mut sum = 0;
        let mut ans = 0;
        for i in 1..=n {
            if sum + i > max_sum {
                break;
            }
            if set.contains(&i) {
                continue;
            }
            sum += i;
            ans += 1;
        }
        ans
    }
}
```

### **C**

```c
int cmp(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}

int maxCount(int *banned, int bannedSize, int n, int maxSum) {
    qsort(banned, bannedSize, sizeof(int), cmp);
    int sum = 0;
    int ans = 0;
    for (int i = 1, j = 0; i <= n; i++) {
        if (sum + i > maxSum) {
            break;
        }
        if (j < bannedSize && i == banned[j]) {
            while (j < bannedSize && i == banned[j]) {
                j++;
            }
        } else {
            sum += i;
            ans++;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
