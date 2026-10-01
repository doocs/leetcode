---
comments: true
difficulty: Medium
tags:
    - Array
    - Two Pointers
    - Sorting
---

<!-- problem:start -->

# [16. 3Sum Closest](https://leetcode.com/problems/3sum-closest)

[中文文档](/solution/0000-0099/0016.3Sum%20Closest/README.md)

## Mô tả

<!-- description:start -->

<p>Cho một mảng số nguyên <code>nums</code> có độ dài <code>n</code> và một số nguyên <code>target</code>, hãy tìm ba số nguyên ở <strong>các chỉ số phân biệt</strong> trong <code>nums</code> sao cho tổng của chúng gần với <code>target</code> nhất.</p>

<p>Trả về <em>tổng của ba số nguyên</em>.</p>

<p>Bạn có thể giả sử rằng mỗi đầu vào sẽ có đúng một nghiệm.</p>

<p>&nbsp;</p>
<p><strong class="example">Ví dụ 1:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [-1,2,1,-4], target = 1
<strong>Đầu ra:</strong> 2
<strong>Giải thích:</strong> Tổng gần với target nhất là 2. (-1 + 2 + 1 = 2).
</pre>

<p><strong class="example">Ví dụ 2:</strong></p>

<pre>
<strong>Đầu vào:</strong> nums = [0,0,0], target = 1
<strong>Đầu ra:</strong> 0
<strong>Giải thích:</strong> Tổng gần với target nhất là 0. (0 + 0 + 0 = 0).
</pre>

<p>&nbsp;</p>
<p><strong>Ràng buộc:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 500</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Lời giải

<!-- solution:start -->

### Lời giải 1: Sắp xếp + Hai con trỏ

<!-- thinking:start -->

> **Tư duy**
>
> Ý tưởng đầu tiên là thử mọi bộ ba và giữ lại tổng gần với $target$ nhất. Cách này có độ phức tạp $O(n^3)$. Với $n\le 1000$, đó là khoảng $10^9$ phép toán và sẽ không vượt qua được.
>
> Nút thắt vẫn là việc thử vét cạn hai số còn lại sau khi cố định một số. Chúng ta không cần mọi bộ ba, mà chỉ cần tổng gần $target$ nhất. Sau khi sắp xếp, nếu một tổng quá lớn thì chỉ có thể trở nên gần hơn bằng cách di chuyển đầu phải sang trái; nếu tổng quá nhỏ thì phải di chuyển đầu trái sang phải. Một kết quả khớp chính xác đã là tối ưu.
>
> Vì vậy, chúng ta liệt kê số đầu tiên và thu hẹp phần còn lại bằng hai con trỏ, đồng thời theo dõi độ lệch nhỏ nhất. Đáp án là duy nhất, nên chúng ta không cần bỏ qua các phần tử trùng lặp.

<!-- thinking:end -->

Trước hết, chúng ta sắp xếp mảng, sau đó duyệt qua mảng. Với mỗi phần tử $nums[i]$, chúng ta dùng các con trỏ $j$ và $k$ lần lượt trỏ tới $i+1$ và $n-1$, rồi tính tổng của ba số. Nếu tổng của ba số bằng $target$, chúng ta trả về trực tiếp $target$. Nếu không, chúng ta cập nhật đáp án dựa trên độ lệch so với $target$. Nếu tổng của ba số lớn hơn $target$, chúng ta di chuyển $k$ sang trái một vị trí, ngược lại, chúng ta di chuyển $j$ sang phải một vị trí.

Độ phức tạp thời gian là $O(n^2)$, và độ phức tạp không gian là $O(\log n)$. Trong đó, $n$ là độ dài của mảng.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        nums.sort()
        n = len(nums)
        ans = inf
        for i, v in enumerate(nums):
            j, k = i + 1, n - 1
            while j < k:
                t = v + nums[j] + nums[k]
                if t == target:
                    return t
                if abs(t - target) < abs(ans - target):
                    ans = t
                if t > target:
                    k -= 1
                else:
                    j += 1
        return ans
```

#### Java

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 1 << 30;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int t = nums[i] + nums[j] + nums[k];
                if (t == target) {
                    return t;
                }
                if (Math.abs(t - target) < Math.abs(ans - target)) {
                    ans = t;
                }
                if (t > target) {
                    --k;
                } else {
                    ++j;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int ans = 1 << 30;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int t = nums[i] + nums[j] + nums[k];
                if (t == target) return t;
                if (abs(t - target) < abs(ans - target)) ans = t;
                if (t > target)
                    --k;
                else
                    ++j;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func threeSumClosest(nums []int, target int) int {
	sort.Ints(nums)
	ans := 1 << 30
	n := len(nums)
	for i, v := range nums {
		j, k := i+1, n-1
		for j < k {
			t := v + nums[j] + nums[k]
			if t == target {
				return t
			}
			if abs(t-target) < abs(ans-target) {
				ans = t
			}
			if t > target {
				k--
			} else {
				j++
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function threeSumClosest(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    let ans: number = 1 << 30;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            const t: number = nums[i] + nums[j] + nums[k];
            if (t === target) {
                return t;
            }
            if (Math.abs(t - target) < Math.abs(ans - target)) {
                ans = t;
            }
            if (t > target) {
                --k;
            } else {
                ++j;
            }
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumClosest = function (nums, target) {
    nums.sort((a, b) => a - b);
    let ans = 1 << 30;
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            const t = nums[i] + nums[j] + nums[k];
            if (t === target) {
                return t;
            }
            if (Math.abs(t - target) < Math.abs(ans - target)) {
                ans = t;
            }
            if (t > target) {
                --k;
            } else {
                ++j;
            }
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int ThreeSumClosest(int[] nums, int target) {
        Array.Sort(nums);
        int ans = 1 << 30;
        int n = nums.Length;
        for (int i = 0; i < n; ++i) {
            int j = i + 1, k = n - 1;
            while (j < k) {
                int t = nums[i] + nums[j] + nums[k];
                if (t == target) {
                    return t;
                }
                if (Math.Abs(t - target) < Math.Abs(ans - target)) {
                    ans = t;
                }
                if (t > target) {
                    --k;
                } else {
                    ++j;
                }
            }
        }
        return ans;
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param int[] $nums
     * @param int $target
     * @return int
     */

    function threeSumClosest($nums, $target) {
        $n = count($nums);
        $closestSum = $nums[0] + $nums[1] + $nums[2];
        $minDiff = abs($closestSum - $target);

        sort($nums);

        for ($i = 0; $i < $n - 2; $i++) {
            $left = $i + 1;
            $right = $n - 1;

            while ($left < $right) {
                $sum = $nums[$i] + $nums[$left] + $nums[$right];
                $diff = abs($sum - $target);

                if ($diff < $minDiff) {
                    $minDiff = $diff;
                    $closestSum = $sum;
                } elseif ($sum < $target) {
                    $left++;
                } elseif ($sum > $target) {
                    $right--;
                } else {
                    return $sum;
                }
            }
        }

        return $closestSum;
    }
}
```

#### C

```c
int cmp(const void* a, const void* b) {
    return (*(int*) a - *(int*) b);
}

int threeSumClosest(int* nums, int numsSize, int target) {
    qsort(nums, numsSize, sizeof(int), cmp);
    int ans = 1 << 30;
    for (int i = 0; i < numsSize; ++i) {
        int j = i + 1, k = numsSize - 1;
        while (j < k) {
            int t = nums[i] + nums[j] + nums[k];
            if (t == target) {
                return t;
            }
            if (abs(t - target) < abs(ans - target)) {
                ans = t;
            }
            if (t > target) {
                --k;
            } else {
                ++j;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
