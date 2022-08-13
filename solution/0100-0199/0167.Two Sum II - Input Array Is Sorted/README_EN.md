# [167. Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted)

[中文文档](/solution/0100-0199/0167.Two%20Sum%20II%20-%20Input%20array%20is%20sorted/README.md)

## Description

<p>Given a <strong>1-indexed</strong> array of integers <code>numbers</code> that is already <strong><em>sorted in non-decreasing order</em></strong>, find two numbers such that they add up to a specific <code>target</code> number. Let these two numbers be <code>numbers[index<sub>1</sub>]</code> and <code>numbers[index<sub>2</sub>]</code> where <code>1 &lt;= index<sub>1</sub> &lt; index<sub>2</sub> &lt;= numbers.length</code>.</p>

<p>Return<em> the indices of the two numbers, </em><code>index<sub>1</sub></code><em> and </em><code>index<sub>2</sub></code><em>, <strong>added by one</strong> as an integer array </em><code>[index<sub>1</sub>, index<sub>2</sub>]</code><em> of length 2.</em></p>

<p>The tests are generated such that there is <strong>exactly one solution</strong>. You <strong>may not</strong> use the same element twice.</p>

<p>Your solution must use only constant extra space.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> numbers = [<u>2</u>,<u>7</u>,11,15], target = 9
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> The sum of 2 and 7 is 9. Therefore, index<sub>1</sub> = 1, index<sub>2</sub> = 2. We return [1, 2].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> numbers = [<u>2</u>,3,<u>4</u>], target = 6
<strong>Output:</strong> [1,3]
<strong>Explanation:</strong> The sum of 2 and 4 is 6. Therefore index<sub>1</sub> = 1, index<sub>2</sub> = 3. We return [1, 3].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> numbers = [<u>-1</u>,<u>0</u>], target = -1
<strong>Output:</strong> [1,2]
<strong>Explanation:</strong> The sum of -1 and 0 is -1. Therefore index<sub>1</sub> = 1, index<sub>2</sub> = 2. We return [1, 2].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= numbers.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= numbers[i] &lt;= 1000</code></li>
	<li><code>numbers</code> is sorted in <strong>non-decreasing order</strong>.</li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
	<li>The tests are generated such that there is <strong>exactly one solution</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

Binary search:

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        n = len(numbers)
        for i in range(n - 1):
            x = target - numbers[i]
            j = bisect.bisect_left(numbers, x, lo=i + 1)
            if j != n and numbers[j] == x:
                return [i + 1, j + 1]
        return [-1, -1]
```

Two pointers:

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        i, j = 1, len(numbers)
        while i < j:
            x = numbers[i - 1] + numbers[j - 1]
            if x == target:
                return [i, j]
            if x < target:
                i += 1
            else:
                j -= 1
        return [-1, -1]
```

### **Java**

Binary search:

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0, n = numbers.length; i < n - 1; ++i) {
            int x = target - numbers[i];
            int left = i + 1, right = n - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (numbers[mid] >= x) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (numbers[left] == x) {
                return new int[]{i + 1, left + 1};
            }
        }
        return new int[]{-1, -1};
    }
}
```

Two pointers:

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 1, j = numbers.length;
        while (i < j) {
            int x = numbers[i - 1] + numbers[j - 1];
            if (x == target) {
                return new int[]{i, j};
            }
            if (x < target) {
                ++i;
            } else {
                --j;
            }
        }
        return new int[]{-1, -1};
    }
}
```

### **TypeScript**

Binary search:

```ts
function twoSum(numbers: number[], target: number): number[] {
    for (let i = 0, n = numbers.length; i < n - 1; ++i) {
        const x = target - numbers[i];
        let left = i + 1,
            right = n - 1;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (numbers[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (numbers[left] == x) {
            return [i + 1, left + 1];
        }
    }
    return [-1, -1];
}
```

Two pointers:

```ts
function twoSum(numbers: number[], target: number): number[] {
    let i = 1,
        j = numbers.length;
    while (i < j) {
        const x = numbers[i - 1] + numbers[j - 1];
        if (x == target) {
            return [i, j];
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
    return [-1, -1];
}
```

### **C++**

Binary search:

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        for (int i = 0, n = numbers.size(); i < n - 1; ++i) {
            int x = target - numbers[i];
            int j = lower_bound(numbers.begin() + i + 1, numbers.end(), x) - numbers.begin();
            if (j != n && numbers[j] == x) return {i + 1, j + 1};
        }
        return {-1, -1};
    }
};
```

Two pointers:

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int i = 1, j = numbers.size();
        while (i < j)
        {
            int x = numbers[i - 1] + numbers[j - 1];
            if (x == target) return {i, j};
            if (x < target) ++i;
            else --j;
        }
        return {-1, -1};
    }
};
```

### **Go**

Binary search:

```go
func twoSum(numbers []int, target int) []int {
	for i, n := 0, len(numbers); i < n-1; i++ {
		x := target - numbers[i]
		left, right := i+1, n-1
		for left < right {
			mid := (left + right) >> 1
			if numbers[mid] >= x {
				right = mid
			} else {
				left = mid + 1
			}
		}
		if numbers[left] == x {
			return []int{i + 1, left + 1}
		}
	}
	return []int{-1, -1}
}
```

Two pointers:

```go
func twoSum(numbers []int, target int) []int {
	i, j := 1, len(numbers)
	for i < j {
		x := numbers[i-1] + numbers[j-1]
		if x == target {
			return []int{i, j}
		}
		if x < target {
			i++
		} else {
			j--
		}
	}
	return []int{-1, -1}
}
```

### **JavaScript**

Binary search:

```js
/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (numbers, target) {
    for (let i = 0, n = numbers.length; i < n - 1; ++i) {
        const x = target - numbers[i];
        let left = i + 1,
            right = n - 1;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (numbers[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (numbers[left] == x) {
            return [i + 1, left + 1];
        }
    }
    return [-1, -1];
};
```

Two pointers:

```js
/**
 * @param {number[]} numbers
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (numbers, target) {
    let i = 1,
        j = numbers.length;
    while (i < j) {
        const x = numbers[i - 1] + numbers[j - 1];
        if (x == target) {
            return [i, j];
        }
        if (x < target) {
            ++i;
        } else {
            --j;
        }
    }
    return [-1, -1];
};
```

### **Rust**

```rust
use std::cmp::Ordering;

impl Solution {
    pub fn two_sum(numbers: Vec<i32>, target: i32) -> Vec<i32> {
        let n = numbers.len();
        let mut l = 0;
        let mut r = n - 1;
        loop {
            match (numbers[l] + numbers[r]).cmp(&target) {
                Ordering::Less => l += 1,
                Ordering::Greater => r -= 1,
                Ordering::Equal => break,
            }
        }
        vec![l as i32 + 1, r as i32 + 1]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
