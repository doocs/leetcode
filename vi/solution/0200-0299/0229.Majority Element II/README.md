---
comments: true
difficulty: Medium
tags:
    - Array
    - Hash Table
    - Counting
    - Sorting
    - Boyer-Moore Voting
---

<!-- problem:start -->

# [229. Majority Element II](https://leetcode.com/problems/majority-element-ii)

[中文文档](/solution/0200-0299/0229.Majority%20Element%20II/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên có kích thước <code>n</code>, hãy tìm tất cả các phần tử xuất hiện nhiều hơn <code>&lfloor;n / 3&rfloor;</code> lần.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [3,2,3]
<strong>Đầu ra:</strong> [3]
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1]
<strong>Đầu ra:</strong> [1]
</pre>

<p><strong class="example">Ví dụ 3:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [1,2]
<strong>Đầu ra:</strong> [1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Câu hỏi mở rộng:</strong> Bạn có thể giải bài toán trong thời gian tuyến tính và với không gian <code>O(1)</code> không?</p>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1

<!-- thinking:start -->

> **Tư duy**
>
> Có nhiều nhất hai giá trị có thể xuất hiện nhiều hơn $\lfloor n/3\rfloor$ lần. Dùng bảng băm được nhưng tốn thêm bộ nhớ. Boyer–Moore có thể duy trì hai ứng viên và các bộ đếm triệt tiêu lẫn nhau.
>
> Khi khớp, tăng một bộ đếm; nếu không, giảm cả hai bộ đếm khi chúng dương. Lượt duyệt thứ hai xác minh tần số thực tế.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def majorityElement(self, nums: List[int]) -> List[int]:
        n1 = n2 = 0
        m1, m2 = 0, 1
        for m in nums:
            if m == m1:
                n1 += 1
            elif m == m2:
                n2 += 1
            elif n1 == 0:
                m1, n1 = m, 1
            elif n2 == 0:
                m2, n2 = m, 1
            else:
                n1, n2 = n1 - 1, n2 - 1
        return [m for m in [m1, m2] if nums.count(m) > len(nums) // 3]
```

#### Java

```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n1 = 0, n2 = 0;
        int m1 = 0, m2 = 1;
        for (int m : nums) {
            if (m == m1) {
                ++n1;
            } else if (m == m2) {
                ++n2;
            } else if (n1 == 0) {
                m1 = m;
                ++n1;
            } else if (n2 == 0) {
                m2 = m;
                ++n2;
            } else {
                --n1;
                --n2;
            }
        }
        List<Integer> ans = new ArrayList<>();
        n1 = 0;
        n2 = 0;
        for (int m : nums) {
            if (m == m1) {
                ++n1;
            } else if (m == m2) {
                ++n2;
            }
        }
        if (n1 > nums.length / 3) {
            ans.add(m1);
        }
        if (n2 > nums.length / 3) {
            ans.add(m2);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> majorityElement(vector<int>& nums) {
        int n1 = 0, n2 = 0;
        int m1 = 0, m2 = 1;
        for (int m : nums) {
            if (m == m1)
                ++n1;
            else if (m == m2)
                ++n2;
            else if (n1 == 0) {
                m1 = m;
                ++n1;
            } else if (n2 == 0) {
                m2 = m;
                ++n2;
            } else {
                --n1;
                --n2;
            }
        }
        vector<int> ans;
        if (count(nums.begin(), nums.end(), m1) > nums.size() / 3) ans.push_back(m1);
        if (count(nums.begin(), nums.end(), m2) > nums.size() / 3) ans.push_back(m2);
        return ans;
    }
};
```

#### Go

```go
func majorityElement(nums []int) []int {
	var n1, n2 int
	m1, m2 := 0, 1
	for _, m := range nums {
		if m == m1 {
			n1++
		} else if m == m2 {
			n2++
		} else if n1 == 0 {
			m1, n1 = m, 1
		} else if n2 == 0 {
			m2, n2 = m, 1
		} else {
			n1, n2 = n1-1, n2-1
		}
	}
	n1, n2 = 0, 0
	for _, m := range nums {
		if m == m1 {
			n1++
		} else if m == m2 {
			n2++
		}
	}
	var ans []int
	if n1 > len(nums)/3 {
		ans = append(ans, m1)
	}
	if n2 > len(nums)/3 {
		ans = append(ans, m2)
	}
	return ans
}
```

#### C#

```cs
public class Solution {
    public IList<int> MajorityElement(int[] nums) {
        int n1 = 0, n2 = 0;
        int m1 = 0, m2 = 1;
        foreach (int m in nums) {
            if (m == m1) {
                ++n1;
            }
            else if (m == m2) {
                ++n2;
            }
            else if (n1 == 0) {
                m1 = m;
                ++n1;
            }
            else if (n2 == 0) {
                m2 = m;
                ++n2;
            }
            else {
                --n1;
                --n2;
            }
        }
        var ans = new List<int>();
        ans.Add(m1);
        ans.Add(m2);
        return ans.Where(m => nums.Count(n => n == m) > nums.Length / 3).ToList();
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer[]
     */
    function majorityElement($nums) {
        $rs = [];
        $n = count($nums);
        for ($i = 0; $i < $n; $i++) {
            $hashmap[$nums[$i]] += 1;
            if ($hashmap[$nums[$i]] > $n / 3) {
                array_push($rs, $nums[$i]);
            }
        }
        return array_unique($rs);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
