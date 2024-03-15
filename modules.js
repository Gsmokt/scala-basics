// have no idea, why closure in bind
// skeleton stupid as fuck,
// but if think about it more deeply, not just about chars
// if variable is module, if module is worker
// if workers change bindign with recusive loop

var a = (function one() {
  const vr = 1;
  return {
    methodOne() {
      return vr;
    },
  };
})();

var b = (function methodTwo() {
  return {};
})();

const c = a.methodOne.bind(b.methodTwo);
console.log(c());

function method2(a) {
  const b = a;
  return {
    num: 2,
    multiple(c) {
      console.log(c);
      return this.num * b;
    },
  };
}

function method3(a) {
  const c = a;
  return {
    num: 2,
    multiple() {
      return this.num * c;
    },
    method1(params, arg) {
      console.log(c);
      console.log(new arg(params).name);
      return this.multiple(c);
    },
  };
}

// const method2 = (function name(a) {
//   const b = a;
//   return {
//     num: 2,
//     multiple() {
//       return this.num * b;
//     },
//   };
// })();

function method1(params, arg) {
  const obj = arg(params);
  return this.multiple();
}

function b(s) {
  return s;
  // return {
  //   num: s,
  // };
}

const a = method3("Hello").method1.bind(method2(2), { name: "jack" });
console.log(a(b));

// function method2(){
// 	const a = 1
// 	return {
// 		key:value
// 	}
// }

// function method1(x){
// 	return this.value * x
// }

// const a = method1.bind(method2(), 20)
