// the typescript way, simple example of wrapper function
// but it's sad with ts types, ruined all potenial
// to make whatever you want
// also it's gonna be totally unreadable with flatMaps,
// scala syntactic sugar makes huge difference

interface NumberWithLogs {
  results: number;
  logs: string[];
}

function square(x: number): NumberWithLogs {
  return {
    results: x * x,
    logs: [`Squared ${x} to get ${x * x}`],
  };
}

function addOne(x: number): NumberWithLogs {
  return {
    results: x + 1,
    logs: [`Added 1 to ${x} to get ${x + 1}`],
  };
}

function wrapWithLogic(x: number): NumberWithLogs {
  return {
    results: x,
    logs: [],
  };
}

function runWithLogs(
  input: NumberWithLogs,
  transform: (_: number) => NumberWithLogs
): NumberWithLogs {
  const newNumberWithLogs = transform(input.results);
  return {
    results: newNumberWithLogs.results,
    logs: [...input.logs, ...newNumberWithLogs.logs],
  };
}

const a = wrapWithLogic(5);
const b = runWithLogs(a, addOne);
const c = runWithLogs(b, square);

type Some<T> = {
  _tag: "Some";
  value: T;
};

type None = {
  _tag: "None";
};

type Option<T> = Some<T> | None;

const none: Option<never> = {
  _tag: "None",
};

function some<T>(x: T): Option<T> {
  return {
    _tag: "Some",
    value: x,
  };
}

function run<T>(input: Option<T>, transform: (_: T) => Option<T>): Option<T> {
  if (input._tag == "None") {
    return input;
  }

  return transform(input.value);
}

const go = (x: string): Option<String> =>
  !(x === typeof Number) ? none : some(x);

console.log(go("Hello"));
