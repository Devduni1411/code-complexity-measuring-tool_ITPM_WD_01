let { Menu } = require("electron");

const { app, BrowserWindow } = require('electron');

let win;
var exec, child;

function createWindow () {

  Menu.setApplicationMenu(null)

  win = new BrowserWindow({
    width: 1900,
    height: 1000,
    backgroundColor: '#ffffff',
    webPreferences: {
      nativeWindowOpen: true
    },
  });

  win.loadURL(`file://${__dirname}/dist/index.html`);

  win.on('closed', function () {
    win = null
  });
}

app.on('ready', createWindow);

app.on('window-all-closed', function () {
  if (process.platform !== 'darwin') {
    app.quit()
  }
});

app.on('activate', function () {
  if (win === null) {
    createWindow()
  }
});
