---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2618.Check%20if%20Object%20Instance%20of%20Class/README.md
tags:
    - JavaScript
---

<!-- problem:start -->

# [2618. 检查是否是类的对象实例](https://leetcode.cn/problems/check-if-object-instance-of-class)

[English Version](/solution/2600-2699/2618.Check%20if%20Object%20Instance%20of%20Class/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请你编写一个函数，检查给定的值是否是给定类或超类的实例。</p>

<p>可以传递给函数的数据类型没有限制。例如，值或类可能是&nbsp; <code>undefined</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>func = () =&gt; checkIfInstance(new Date(), Date)
<b>输出：</b>true
<strong>解释：</strong>根据定义，Date 构造函数返回的对象是 Date 的一个实例。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>func = () =&gt; { class Animal {}; class Dog extends Animal {}; return checkIfInstance(new Dog(), Animal); }
<b>输出：</b>true
<strong>解释：</strong>
class Animal {};
class Dog extends Animal {};
checkIfInstanceOf(new Dog(), Animal); // true

Dog 是 Animal 的子类。因此，Dog 对象同时是 Dog 和 Animal 的实例。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>func = () =&gt; checkIfInstance(Date, Date)
<b>输出：</b>false
<strong>解释：</strong>日期的构造函数在逻辑上不能是其自身的实例。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<b>输入：</b>func = () =&gt; checkIfInstance(5, Number)
<b>输出：</b>true
<strong>解释：</strong>5 是一个 Number。注意，"instanceof" 关键字将返回 false。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### TypeScript

```ts
function checkIfInstanceOf(obj: any, classFunction: any): boolean {
    if (classFunction === null || classFunction === undefined) {
        return false;
    }
    while (obj !== null && obj !== undefined) {
        const proto = Object.getPrototypeOf(obj);
        if (proto === classFunction.prototype) {
            return true;
        }
        obj = proto;
    }
    return false;
}

/**
 * checkIfInstanceOf(new Date(), Date); // true
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
