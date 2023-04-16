# [2618. Check if Object Instance of Class](https://leetcode.com/problems/check-if-object-instance-of-class)

[中文文档](/solution/2600-2699/2618.Check%20if%20Object%20Instance%20of%20Class/README.md)

## Description

<p>Write a function that checks if a given object is an instance of a given class or superclass.</p>

<p>There are&nbsp;no constraints on the data types that can be passed to the function.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> func = () =&gt; checkIfInstance(new Date(), Date)
<strong>Output:</strong> true
<strong>Explanation: </strong>The object returned by the Date constructor is, by definition, an instance of Date.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> func = () =&gt; { class Animal {}; class Dog extends Animal {}; return checkIfInstance(new Dog(), Animal); }
<strong>Output:</strong> true
<strong>Explanation:</strong>
class Animal {};
class Dog extends Animal {};
checkIfInstance(new Dog(), Animal); // true

Dog is a subclass of Animal. Therefore, a Dog object is an instance of both Dog and Animal.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> func = () =&gt; checkIfInstance(Date, Date)
<strong>Output:</strong> false
<strong>Explanation: </strong>A date constructor cannot logically be an instance of itself.
</pre>

<p><strong class="example">Example 4:</strong></p>

<pre>
<strong>Input:</strong> func = () =&gt; checkIfInstance(5, Number)
<strong>Output:</strong> true
<strong>Explanation: </strong>5 is a Number. Note that the &quot;instanceof&quot; keyword would return false.
</pre>

## Solutions

<!-- tabs:start -->

### **TypeScript**

```ts
function checkIfInstanceOf(obj: any, classFunction: any): boolean {
    if (classFunction == null) {
        return false;
    }
    while (obj != null) {
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

### **...**

```

```

<!-- tabs:end -->
