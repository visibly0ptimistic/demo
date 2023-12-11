  const baseUrl = 'https://victors-demos-f07d6a086e9b.herokuapp.com/stack';

  // Initialize stackData
  let stackData = [];

  const svg = d3.select("#stack-svg");
  const svgRect = svg.node().getBoundingClientRect();
  const width = svgRect.width;
  const height = svgRect.height;

function updateStack() {
  console.log("Updating stack with data:", stackData);

  // RECTANGLES
  const rects = svg.selectAll("rect").data(stackData, d => d);
  rects.exit().remove(); // This removes the top-most rectangle (last element in the array)
  rects.enter().append("rect")
      .merge(rects)
      .attr("y", (d, i) => height - (stackData.length - i) * 30) // Position from the bottom but reverse the order
      .attr("x", 0) // Start from left
      .attr("height", 28)
      .attr("width", d => 20 + (d - 1) * 20)
      .attr("fill", "steelblue");

  // TEXTS
  const texts = svg.selectAll("text").data(stackData, d => d);
  texts.exit().remove(); // This removes the top-most text (last element in the array)
  texts.enter().append("text")
      .merge(texts)
      .attr("y", (d, i) => height - (stackData.length - i) * 30 + 20) // Position texts in reverse order
      .attr("x", 10) // A bit to the right from the left edge
      .attr("fill", "white")
      .text(d => d);
}


  // Function to fetch API
  async function fetchApi(method, endpoint, params = {}) {
    const url = new URL(`${baseUrl}${endpoint}`);
    Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));
    const response = await fetch(url, { method });
    const data = await response.json();
    return data;
  }

  // Function to refresh stack from server
async function refreshStack() {
  try {
    const response = await fetch(`${baseUrl}/stackList`);
    const data = await response.json();
    console.log("Data received from server: ", data);  // Debugging line
    if (data && data.stack) {
      stackData = data.stack;
    } else {
      stackData = [];
      console.error("Received unexpected data format from server"); // Debugging line
    }
    updateStack();
  } catch (error) {
    console.error(`Error refreshing stack: ${error}`);
  }
}

  // Function to log messages
function log(message) {
  const logDiv = document.getElementById('log');
  const newLogEntry = document.createElement('div');
  newLogEntry.textContent = message;
  logDiv.insertBefore(newLogEntry, logDiv.firstChild); // Prepend the new entry
}

async function pushItem() {
  const value = document.getElementById('push-value').value;
  const userMaxSize = parseInt(document.getElementById('max-size').value, 10);
  const maxSize = Math.min(userMaxSize, 13); // Cap the maximum size at 13

  const intValue = parseInt(value, 10);

  if (value === null || value.trim() === '') {
    log("Value cannot be null or empty.");
    return;
  }

  if (isNaN(intValue) || intValue <= 0) {
    log("Value should be a positive integer.");
    return;
  }

  // The updated check for maxSize
  if (isNaN(maxSize) || maxSize <= 0) {
    log("Maximum Stack Size must be set to a positive integer before pushing.");
    return;
  }

  if (stackData.length >= maxSize) {
    log("Stack is full. Can't push anymore.");
    return;
  }

  try {
    const result = await fetchApi('POST', '/push', { value: intValue });
    if (result.error) {
      // Log the error message if there's an error in the response
      log(`Error: ${result.error}`);
    } else {
      // Only proceed if there's no error
      log(`${result.message}`);
      if (result.stack) {
        stackData = result.stack;
        updateStack();
      }
    }
  } catch (error) {
    log(`Push Error: ${error.message}`);
  }

  refreshStack();
}


async function popItem() {
  // Check if stack is empty
  if (stackData.length === 0) {
    log("Stack is empty. Nothing to pop.");
    return;
  }
  
  try {
    const result = await fetchApi('POST', '/pop');
    log(`${result.message}`);
  } catch (error) {
    log(`Pop Error: ${error.message}`);
  }
  refreshStack();
}

async function peekItem() {
  // Check if stack is empty
  if (stackData.length === 0) {
    log("Stack is empty. Nothing to peek at.");
    return;
  }
  
  try {
    const result = await fetchApi('GET', '/peek');
    log(`${result.message}`);
  } catch (error) {
    log(`Peek Error: ${error.message}`);
  }
  refreshStack();
}

  async function isEmpty() {
    try {
      const result = await fetchApi('GET', '/isEmpty');
      log(`${result.message}`);
    } catch (error) {
      log(`Is Empty Error: ${error}`);
    }
    refreshStack();
  }

async function isFull() {
  try {
    const result = await fetchApi('GET', '/isFull');
    log(`${result.message}`);
  } catch (error) {
    log(`Is Full Error: ${error}`);
  }
  refreshStack();
}


async function setMaxSize() {
  const newSize = parseInt(document.getElementById('max-size').value, 10);
  const maxAllowedSize = 13; // Define the maximum allowed stack size

  if (isNaN(newSize) || newSize <= 0 || newSize > maxAllowedSize) {
    log(`Maximum Stack Size must be a positive integer and cannot exceed ${maxAllowedSize}.`);
    return;
  }

  try {
    const result = await fetchApi('POST', '/setMaxSize', { newSize: newSize });
    if (result.error) {
      log(`Error: ${result.error}`);
    } else {
      log(`${result.message}`);
      refreshStack();  // Refresh the stack to reflect the new size
    }
  } catch (error) {
    log(`Set Max Size Error: ${error.message}`);
  }
}

  async function popAll() {
    try {
      const result = await fetchApi('POST', '/popAll');
      log(`${result.message}`);
    } catch (error) {
      log(`Pop All Error: ${error}`);
    }
    refreshStack();
  }

function clearLog() {
  const logDiv = document.getElementById('log');
  while (logDiv.firstChild) {
    logDiv.removeChild(logDiv.firstChild);
  }
}

  // Initialize stack visualization
  refreshStack();