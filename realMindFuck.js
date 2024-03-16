// ok, so, we are here, let's try to make so compose
// with recursive
// case if stacking is not the reason but way to do some
// action

const arr = [() => {}, () => {}, () => {}, () => {}];
const func = () => {};

const compose = (acc, next, _, array) => {
  const obj = {};
  let result = {};

  const a = (x) => {
    if (x <= 0) return func(result);
    if ("num" in obj) {
      obj.num++;
      result = acc(next(obj));
    } else obj.num = 0;
    return a(--x);
  };
  return a;
};

const composeAll = (...fns) => {
  return fns.reduce(compose);
};
