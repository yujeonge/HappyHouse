module.exports = {
  devServer: {
    proxy: {
      "/api": {
        target: "https://apis.data.go.kr",
        ws: true,
        changeOrigin: true,
        pathRewirte: {
          "^/api": "",
        },
      },
    },
  },
};
