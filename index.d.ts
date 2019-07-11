declare module '@brigad/react-native-disable-battery-optimizations' {
  function openBatteryModal(): void;
  function isBatteryOptimizationEnabled(): Promise<boolean>;
}
