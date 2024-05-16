---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2758.Next%20Day/README_EN.md
---

<!-- problem:start -->

# [2758. Next Day 🔒](https://leetcode.com/problems/next-day)

[中文文档](/solution/2700-2799/2758.Next%20Day/README.md)

## Description

<!-- description:start -->

<p>Write code that enhances all date objects such that you can call the <code>date.nextDay()</code>&nbsp;method on any date object and it will return&nbsp;the next day in the format <em>YYYY-MM-DD</em> as a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> date = &quot;2014-06-20&quot;
<strong>Output:</strong> &quot;2014-06-21&quot;
<strong>Explanation:</strong> 
const date = new Date(&quot;2014-06-20&quot;);
date.nextDay(); // &quot;2014-06-21&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> date = &quot;2017-10-31&quot;
<strong>Output:</strong> &quot;2017-11-01&quot;
<strong>Explanation:</strong> The day after 2017-10-31 is 2017-11-01.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>new Date(date)</code> is a valid date object</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```ts
declare global {
    interface Date {
        nextDay(): string;
    }
}

Date.prototype.nextDay = function () {
    const date = new Date(this.valueOf());
    date.setDate(date.getDate() + 1);
    return date.toISOString().slice(0, 10);
};

/**
 * const date = new Date("2014-06-20");
 * date.nextDay(); // "2014-06-21"
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
