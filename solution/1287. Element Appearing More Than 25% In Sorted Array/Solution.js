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
