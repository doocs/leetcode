var dayOfYear = function (date) {
  var year = date.substr(0, 4) - 0;
  var month = date.substr(5, 2) - 0;
  var day = date.substr(8, 2) - 0;
  var dayNumber = 0;
  for (var m = 1; m < month; m++) {
    if (m == 4 || m == 6 || m == 9 || m == 11) {
      dayNumber += 30;
    } else if (m == 2) {
      dayNumber += year % 4 == 0 && year != 1900 ? 29 : 28;
    } else {
      dayNumber += 31;
    }
  }
  return (dayNumber += day);
};
