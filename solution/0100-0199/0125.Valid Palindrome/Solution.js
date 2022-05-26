const isPalindrome1 = function (s) {
  let arr1 = [],
    arr2 = [];
  for (let i = 0; i < s.length; i++) {
    if (s[i] >= "A" && s[i] <= "Z") {
      arr1.push(s[i].toLowerCase());
    }
    if ((s[i] >= "0" && s[i] <= "9") || (s[i] >= "a" && s[i] <= "z")) {
      arr1.push(s[i]);
    }
  }
  arr2 = [...arr1];
  arr2.reverse();
  return arr1.join("") === arr2.join("");
};

const isPalindrome = function (s) {
  function isNumOrAl(a) {
    if (
      (a >= "A" && a <= "Z") ||
      (a >= "0" && a <= "9") ||
      (a >= "a" && a <= "z")
    ) {
      return true;
    } else {
      return false;
    }
  }

  if (s.length === 0) {
    return true;
  }
  let i = 0,
    j = s.length - 1;
  while (i < j) {
    while (i < j && !isNumOrAl(s[i])) {
      i++;
    }
    while (i < j && !isNumOrAl(s[j])) {
      j--;
    }
    if (s[i].toLowerCase() !== s[j].toLowerCase()) {
      return false;
    } else {
      i++;
      j--;
    }
  }
  return true;
};
