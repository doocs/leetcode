---
comments: true
difficulty: Easy
tags:
    - Array
    - Hash Table
    - Sorting
---

<!-- problem:start -->

# [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate)

[中文文档](/solution/0200-0299/0217.Contains%20Duplicate/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code>, hãy trả về <code>true</code> nếu có bất kỳ giá trị nào xuất hiện <strong>ít nhất hai lần</strong> trong mảng, và trả về <code>false</code> nếu mọi phần tử đều khác nhau.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">nums = [1,2,3,1]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">true</span></p>

<p><strong>Giải thích:</strong></p>

<p>Phần tử 1 xuất hiện tại các chỉ số 0 và 3.</p>
</div>

<p><strong class="example">Ví dụ 2:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">false</span></p>

<p><strong>Giải thích:</strong></p>

<p>Tất cả các phần tử đều khác nhau.</p>
</div>

<p><strong class="example">Ví dụ 3:</strong></p>

<div class="example-block">
<p><strong>Đầu vào:</strong> <span class="example-io">nums = [1,1,1,3,3,4,3,2,4,2]</span></p>

<p><strong>Đầu ra:</strong> <span class="example-io">true</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Sắp xếp

<!-- thinking:start -->

> **Tư duy**
>
> Việc so sánh mọi cặp đều có thể thực hiện được nhưng chậm khi $n$ lớn. Các giá trị bằng nhau sẽ nằm cạnh nhau sau khi sắp xếp mảng.
>
> Chúng ta sắp xếp mảng, sau đó kiểm tra xem có hai phần tử liền kề nào bằng nhau hay không.

<!-- thinking:end -->

Đầu tiên, chúng ta sắp xếp mảng `nums`.

Sau đó, chúng ta duyệt qua mảng. Nếu có hai phần tử liền kề bằng nhau, điều đó có nghĩa là mảng có các phần tử trùng lặp, và chúng ta trả về `true` ngay lập tức.

Nếu không, khi kết thúc quá trình duyệt, chúng ta trả về `false`.

Độ phức tạp thời gian là $O(n \times \log n)$, trong đó $n$ là độ dài của mảng `nums`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        return any(a == b for a, b in pairwise(sorted(nums)))
```

#### Java

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        for (int i = 0; i < nums.size() - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func containsDuplicate(nums []int) bool {
	sort.Ints(nums)
	for i, v := range nums[1:] {
		if v == nums[i] {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function containsDuplicate(nums: number[]): boolean {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    for (let i = 1; i < n; i++) {
        if (nums[i - 1] === nums[i]) {
            return true;
        }
    }
    return false;
}
```

#### Rust

```rust
impl Solution {
    pub fn contains_duplicate(mut nums: Vec<i32>) -> bool {
        nums.sort();
        let n = nums.len();
        for i in 1..n {
            if nums[i - 1] == nums[i] {
                return true;
            }
        }
        false
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var containsDuplicate = function (nums) {
    return new Set(nums).size !== nums.length;
};
```

#### C#

```cs
public class Solution {
    public bool ContainsDuplicate(int[] nums) {
        return nums.Distinct().Count() < nums.Length;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Boolean
     */
    function containsDuplicate($nums) {
        $numsUnique = array_unique($nums);
        return count($nums) != count($numsUnique);
    }
}
```

#### C

```c
int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

bool containsDuplicate(int* nums, int numsSize) {
    qsort(nums, numsSize, sizeof(int), cmp);
    for (int i = 1; i < numsSize; i++) {
        if (nums[i - 1] == nums[i]) {
            return 1;
        }
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Lời giải 2: Bảng băm

<!-- thinking:start -->

> **Tư duy**
>
> Việc sắp xếp có độ phức tạp $O(n\log n)$ và làm thay đổi thứ tự mảng. Chỉ cần theo dõi việc một phần tử có thuộc hash set hay không bằng cách duyệt mảng một lần.
>
> Một giá trị đã có trong tập hợp là một phần tử trùng lặp.

<!-- thinking:end -->

Chúng ta duyệt qua mảng và ghi lại các phần tử đã xuất hiện trong bảng băm $s$. Nếu một phần tử xuất hiện lần thứ hai, điều đó có nghĩa là mảng có các phần tử trùng lặp, và chúng ta trả về `true` ngay lập tức.

Độ phức tạp thời gian là $O(n)$, và độ phức tạp không gian là $O(n)$, trong đó $n$ là độ dài của mảng `nums`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        return len(set(nums)) < len(nums)
```

#### Java

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            if (!s.add(num)) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        return s.size() < nums.size();
    }
};
```

#### Go

```go
func containsDuplicate(nums []int) bool {
	s := map[int]bool{}
	for _, v := range nums {
		if s[v] {
			return true
		}
		s[v] = true
	}
	return false
}
```

#### TypeScript

```ts
function containsDuplicate(nums: number[]): boolean {
    return new Set<number>(nums).size !== nums.length;
}
```

#### Rust

```rust
use std::collections::HashSet;
impl Solution {
    pub fn contains_duplicate(nums: Vec<i32>) -> bool {
        nums.iter().collect::<HashSet<&i32>>().len() != nums.len()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
