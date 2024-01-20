# [1538. Guess the Majority in a Hidden Array](https://leetcode.com/problems/guess-the-majority-in-a-hidden-array)

[中文文档](/solution/1500-1599/1538.Guess%20the%20Majority%20in%20a%20Hidden%20Array/README.md)

## Description

<p>We have an integer array <code>nums</code>, where all the integers in <code>nums</code> are <strong>0</strong> or <strong>1</strong>. You will not be given direct access to the array, instead, you will have an <strong>API</strong> <code>ArrayReader</code> which have the following functions:</p>

<ul>
	<li><code>int query(int a, int b, int c, int d)</code>: where <code>0 &lt;= a &lt; b &lt; c &lt; d &lt; ArrayReader.length()</code>. The function returns the distribution of the value of the 4 elements and returns:

    <ul>
    	<li><strong>4 </strong>: if the values of the 4 elements are the same (0 or 1).</li>
    	<li><strong>2</strong> : if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.</li>
    	<li><strong>0 </strong>: if two element have a value equal to 0 and two elements have a value equal to 1.</li>
    </ul>
    </li>
    <li><code>int length()</code>: Returns the size of the array.</li>

</ul>

<p>You are allowed to call <code>query()</code> <b>2 * n times</b> at most where n is equal to <code>ArrayReader.length()</code>.</p>

<p>Return <strong>any</strong> index of the most frequent value in <code>nums</code>, in case of tie, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,0,1,1,1,1]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The following calls to the API
reader.length() // returns 8 because there are 8 elements in the hidden array.
reader.query(0,1,2,3) // returns 2 this is a query that compares the elements nums[0], nums[1], nums[2], nums[3]
// Three elements have a value equal to 0 and one element has value equal to 1 or viceversa.
reader.query(4,5,6,7) // returns 4 because nums[4], nums[5], nums[6], nums[7] have the same value.
we can infer that the most frequent value is found in the last 4 elements.
Index 2, 4, 6, 7 is also a correct answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,1,0]
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,1,0,1,0,1,0]
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>5 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What is the minimum number of calls needed to find the majority element?</p>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
# """
# This is the ArrayReader's API interface.
# You should not implement it, or speculate about its implementation
# """
# class ArrayReader(object):
# 	 # Compares 4 different elements in the array
# 	 # return 4 if the values of the 4 elements are the same (0 or 1).
# 	 # return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
# 	 # return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
#    def query(self, a: int, b: int, c: int, d: int) -> int:
#
# 	 # Returns the length of the array
#    def length(self) -> int:
#


class Solution:
    def guessMajority(self, reader: "ArrayReader") -> int:
        n = reader.length()
        x = reader.query(0, 1, 2, 3)
        a, b = 1, 0
        k = 0
        for i in range(4, n):
            if reader.query(0, 1, 2, i) == x:
                a += 1
            else:
                b += 1
                k = i

        y = reader.query(0, 1, 2, 4)
        if reader.query(1, 2, 3, 4) == y:
            a += 1
        else:
            b += 1
            k = 0
        if reader.query(0, 2, 3, 4) == y:
            a += 1
        else:
            b += 1
            k = 1
        if reader.query(0, 1, 3, 4) == y:
            a += 1
        else:
            b += 1
            k = 2

        if a == b:
            return -1
        return 3 if a > b else k
```

```java
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *   public:
 *     // Compares 4 different elements in the array
 *     // return 4 if the values of the 4 elements are the same (0 or 1).
 *     // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or
 * vice versa.
 *     // return 0 : if two element have a value equal to 0 and two elements have a value equal
 * to 1. public int query(int a, int b, int c, int d);
 *
 *     // Returns the length of the array
 *     public int length();
 * };
 */

class Solution {
    public int guessMajority(ArrayReader reader) {
        int n = reader.length();
        int x = reader.query(0, 1, 2, 3);
        int a = 1, b = 0;
        int k = 0;
        for (int i = 4; i < n; ++i) {
            if (reader.query(0, 1, 2, i) == x) {
                ++a;
            } else {
                ++b;
                k = i;
            }
        }

        int y = reader.query(0, 1, 2, 4);
        if (reader.query(1, 2, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 0;
        }
        if (reader.query(0, 2, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 1;
        }
        if (reader.query(0, 1, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 2;
        }
        if (a == b) {
            return -1;
        }
        return a > b ? 3 : k;
    }
}
```

```cpp
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * class ArrayReader {
 *   public:
 *     // Compares 4 different elements in the array
 *     // return 4 if the values of the 4 elements are the same (0 or 1).
 *     // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
 *     // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
 *     int query(int a, int b, int c, int d);
 *
 *     // Returns the length of the array
 *     int length();
 * };
 */

class Solution {
public:
    int guessMajority(ArrayReader& reader) {
        int n = reader.length();
        int x = reader.query(0, 1, 2, 3);
        int a = 1, b = 0;
        int k = 0;
        for (int i = 4; i < n; ++i) {
            if (reader.query(0, 1, 2, i) == x) {
                ++a;
            } else {
                ++b;
                k = i;
            }
        }

        int y = reader.query(0, 1, 2, 4);
        if (reader.query(1, 2, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 0;
        }
        if (reader.query(0, 2, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 1;
        }
        if (reader.query(0, 1, 3, 4) == y) {
            ++a;
        } else {
            ++b;
            k = 2;
        }
        if (a == b) {
            return -1;
        }
        return a > b ? 3 : k;
    }
};
```

```go
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * type ArrayReader struct {
 * }
 * // Compares 4 different elements in the array
 * // return 4 if the values of the 4 elements are the same (0 or 1).
 * // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
 * // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
 * func (this *ArrayReader) query(a, b, c, d int) int {}
 *
 * // Returns the length of the array
 * func (this *ArrayReader) length() int {}
 */

func guessMajority(reader *ArrayReader) int {
	n := reader.length()
	x := reader.query(0, 1, 2, 3)
	a, b := 1, 0
	k := 0
	for i := 4; i < n; i++ {
		if reader.query(0, 1, 2, i) == x {
			a++
		} else {
			b++
			k = i
		}
	}

	y := reader.query(0, 1, 2, 4)
	if reader.query(1, 2, 3, 4) == y {
		a++
	} else {
		b++
		k = 0
	}
	if reader.query(0, 2, 3, 4) == y {
		a++
	} else {
		b++
		k = 1
	}
	if reader.query(0, 1, 3, 4) == y {
		a++
	} else {
		b++
		k = 2
	}
	if a == b {
		return -1
	}
	if a > b {
		return 3
	}
	return k
}
```

```ts
/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * class ArrayReader {
 *     // Compares 4 different elements in the array
 *     // return 4 if the values of the 4 elements are the same (0 or 1).
 *     // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
 *     // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
 *     query(a: number, b: number, c: number, d: number): number { };
 *
 *     // Returns the length of the array
 *     length(): number { };
 * };
 */

function guessMajority(reader: ArrayReader): number {
    const n = reader.length();
    const x = reader.query(0, 1, 2, 3);
    let a = 1;
    let b = 0;
    let k = 0;
    for (let i = 4; i < n; ++i) {
        if (reader.query(0, 1, 2, i) === x) {
            ++a;
        } else {
            ++b;
            k = i;
        }
    }
    const y = reader.query(0, 1, 2, 4);
    if (reader.query(1, 2, 3, 4) === y) {
        ++a;
    } else {
        ++b;
        k = 0;
    }
    if (reader.query(0, 2, 3, 4) === y) {
        ++a;
    } else {
        ++b;
        k = 1;
    }
    if (reader.query(0, 1, 3, 4) === y) {
        ++a;
    } else {
        ++b;
        k = 2;
    }
    if (a === b) {
        return -1;
    }
    return a > b ? 3 : k;
}
```

<!-- tabs:end -->

<!-- end -->
