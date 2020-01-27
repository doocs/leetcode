1287. Element Appearing More Than 25% In Sorted Array

Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.

```Javascript
const findSpecialInteger = function(arr) {
  let count = 0;
  let item = -1;
  for (var i = 0; i < arr.length; i++) {
      if (item == arr[i]) {
          count++;
      } else {
          item = arr[i];
          count = 1;
      }
      if (count > arr.length * 0.25) {
          return item;
      }
  }
  return item;
};
```
