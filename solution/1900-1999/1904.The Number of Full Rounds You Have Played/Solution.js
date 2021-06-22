/**
 * @param {string} startTime
 * @param {string} finishTime
 * @return {number}
 */
 var numberOfRounds = function(startTime, finishTime) {
    let m1 = toMinutes(startTime), m2 = toMinutes(finishTime);

    if (m1 > m2) {
        m2 += 24 * 60;
    }

    let ans = Math.floor(m2 / 15) - Math.ceil(m1 / 15);
    return ans < 0 ? 0 : ans;
};

function toMinutes(time) {
    let [h, m] = time.split(':');
    return Number(h) * 60 + Number(m);
}