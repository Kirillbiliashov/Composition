'use strict';

const compose = (...fns) => {
    const composed = x => {
        let res = x;
        for (const fn of fns.reverse()) {
            try {
                res = fn(res);
            } catch (error) {
                composed['error'](error);
                res = undefined;
                break;
            }
        }
        return res;
    }
    composed.on = (error, cb) => composed[error] = cb;

    return composed;
};

module.exports = { compose };
