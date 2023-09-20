import * as React from 'react';
import {useState, useEffect} from 'react';
import {
  Alert, UIManager,
  StyleSheet,
  Text,
  Pressable,
  ScrollView,
  Linking,
  View,
  DeviceEventEmitter,
  SafeAreaView,
  Animated,StatusBar,Button, ActivityIndicator
} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { Storyly,close } from 'storyly-react-native';
import DetailPage from './src/DetailPage';
import test from './src/test';

// import PushNotificationIOS from '@react-native-community/push-notification-ios';

function HomeScreen({ navigation }) {

  const HEIGHT = 100;
  const PADDING_TOP = 10;

 const height = new Animated.Value(HEIGHT);
  const paddingTop = new Animated.Value(PADDING_TOP);

  const openStoryly = () => {
    Animated.timing(height, {
      toValue: HEIGHT,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();

    Animated.timing(paddingTop, {
      toValue: PADDING_TOP,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();
  }

  const closeStoryly = () => {
    Animated.timing(height, {
      toValue: 0,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();

    Animated.timing(paddingTop, {
      toValue: 0,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();
  }
  
  // useEffect(() => {
  //  /* PushNotificationIOS.addEventListener('register', onRegistered);
  //   PushNotificationIOS.addEventListener(
  //     'registrationError',
  //     onRegistrationError,
  //   );*/ // 'Registered For Remote Push' onRegistered ve onRegistrationError fonksyionları aşağıda yorum satırında


  //  PushNotificationIOS.addEventListener('notification', onRemoteNotification);
  //   PushNotificationIOS.addEventListener(
  //     'localNotification',
  //     onLocalNotification,
  //   ); 

  //   PushNotificationIOS.requestPermissions({
  //     alert: true,
  //     badge: true,
  //     sound: true,
  //     critical: true,
  //   }).then(
  //     (data) => {
  //       console.log('PushNotificationIOS.requestPermissions', data);
  //     },
  //     (data) => {
  //       console.log('PushNotificationIOS.requestPermissions failed', data);
  //     },
  //   );

  //   return () => {
  //     PushNotificationIOS.removeEventListener('register');
  //     PushNotificationIOS.removeEventListener('registrationError');
  //     PushNotificationIOS.removeEventListener('notification');
  //     PushNotificationIOS.removeEventListener('localNotification');
  //   };
  //   // eslint-disable-next-line react-hooks/exhaustive-deps
  // }, []);

  const sendNotification = () => {
    DeviceEventEmitter.emit('remoteNotificationReceived', {
      remote: true,
      aps: {
        alert: {title: 'title', subtitle: 'subtitle', body: 'body'},
        badge: 1,
        sound: 'default',
        category: 'REACT_NATIVE',
        'content-available': 1,
        'mutable-content': 1,
      },
    });
  };

  const sendSilentNotification = () => {
    DeviceEventEmitter.emit('remoteNotificationReceived', {
      remote: true,
      aps: {
        category: 'REACT_NATIVE',
        'content-available': 1,
      },
    });
  };

  const sendLocalNotification = () => {
    PushNotificationIOS.presentLocalNotification({
      alertTitle: 'Sample Title',
      alertBody: 'Sample local notification',
      applicationIconBadgeNumber: 1,
    });
  };

  const sendLocalNotificationWithSound = () => {
    PushNotificationIOS.addNotificationRequest({
      id: 'notificationWithSound',
      title: 'Sample Title',
      subtitle: 'Sample Subtitle',
      body: 'Sample local notification with custom sound',
      sound: 'customSound.wav',
      badge: 1,
    });
  };

  const scheduleLocalNotification = () => {
    PushNotificationIOS.scheduleLocalNotification({
      alertBody: 'Test Local Notification',
      fireDate: new Date(new Date().valueOf() + 2000).toISOString(),
    });
  };

  const addNotificationRequest = () => {
    PushNotificationIOS.addNotificationRequest({
      id: 'test',
      title: 'title',
      subtitle: 'subtitle',
      body: 'body',
      category: 'test',
      threadId: 'thread-id',
      fireDate: new Date(new Date().valueOf() + 2000),
      repeats: true,
      userInfo: {
        image: 'https://www.github.com/Naturalclar.png',
      },
    });
  };

  const addCriticalNotificationRequest = () => {
    PushNotificationIOS.addNotificationRequest({
      id: 'critical',
      title: 'Critical Alert',
      subtitle: 'subtitle',
      body: 'This is a critical alert',
      category: 'test',
      threadId: 'thread-id',
      isCritical: true,
      fireDate: new Date(new Date().valueOf() + 2000),
      repeats: true,
    });
  };

  const addMultipleRequests = () => {
    PushNotificationIOS.addNotificationRequest({
      id: 'test-1',
      title: 'First',
      subtitle: 'subtitle',
      body: 'First Notification out of 3',
      category: 'test',
      threadId: 'thread-id',
      fireDate: new Date(new Date().valueOf() + 10000),
      repeats: true,
    });

    PushNotificationIOS.addNotificationRequest({
      id: 'test-2',
      title: 'Second',
      subtitle: 'subtitle',
      body: 'Second Notification out of 3',
      category: 'test',
      threadId: 'thread-id',
      fireDate: new Date(new Date().valueOf() + 12000),
      repeats: true,
    });

    PushNotificationIOS.addNotificationRequest({
      id: 'test-3',
      title: 'Third',
      subtitle: 'subtitle',
      body: 'Third Notification out of 3',
      category: 'test',
      threadId: 'thread-id',
      fireDate: new Date(new Date().valueOf() + 14000),
      repeats: true,
    });
  };

  const getPendingNotificationRequests = () => {
    PushNotificationIOS.getPendingNotificationRequests((requests) => {
      Alert.alert('Push Notification Received', JSON.stringify(requests), [
        {
          text: 'Dismiss',
          onPress: null,
        },
      ]);
    });
  };

  const setNotificationCategories = async () => {
    PushNotificationIOS.setNotificationCategories([
      {
        id: 'test',
        actions: [
          {id: 'open', title: 'Open', options: {foreground: true}},
          {
            id: 'ignore',
            title: 'Desruptive',
            options: {foreground: true, destructive: true},
          },
          {
            id: 'text',
            title: 'Text Input',
            options: {foreground: true},
            textInput: {buttonTitle: 'Send'},
          },
        ],
      },
    ]);
    Alert.alert(
      'setNotificationCategories',
      `Set notification category complete`,
      [
        {
          text: 'Dismiss',
          onPress: null,
        },
      ],
    );
  };

  const removeAllPendingNotificationRequests = () => {
    PushNotificationIOS.removeAllPendingNotificationRequests();
  };

  const removePendingNotificationRequests = () => {
    PushNotificationIOS.removePendingNotificationRequests(['test-1', 'test-2']);
  };

  /*const onRegistered = (deviceToken) => {
    Alert.alert('Registered For Remote Push', `Device Token: ${deviceToken}`, [
      {
        text: 'Dismiss',
        onPress: null,
      },
    ]);
  };*/

  /*const onRegistrationError = (error) => {
    Alert.alert(
      'Failed To Register For Remote Push',
      `Error (${error.code}): ${error.message}`,
      [
        {
          text: 'Dismiss',
          onPress: null,
        },
      ],
    );
  };*/

  const onRemoteNotification = (notification) => {
    const isClicked = notification.getData().userInteraction === 1;

    const result = `
      Title:  ${notification.getTitle()};\n
      Subtitle:  ${notification.getSubtitle()};\n
      Message: ${notification.getMessage()};\n
      badge: ${notification.getBadgeCount()};\n
      sound: ${notification.getSound()};\n
      category: ${notification.getCategory()};\n
      content-available: ${notification.getContentAvailable()};\n
      Notification is clicked: ${String(isClicked)}.`;

    if (notification.getTitle() == undefined) {
      Alert.alert('Silent push notification Received', result, [
        {
          text: 'Send local push',
          onPress: sendLocalNotification,
        },
      ]);
    } else {
      Alert.alert('Push Notification Received', result, [
        {
          text: 'Dismiss',
          onPress: null,
        },
      ]);
    }
  };

  const onLocalNotification = (notification) => {
    const isClicked = notification.getData().userInteraction === 1;

    Alert.alert(
      'Local Notification Received',
      `Alert title:  ${notification.getTitle()},
      Alert subtitle:  ${notification.getSubtitle()},
      Alert message:  ${notification.getMessage()},
      Badge: ${notification.getBadgeCount()},
      Sound: ${notification.getSound()},
      Thread Id:  ${notification.getThreadID()},
      Action Id:  ${notification.getActionIdentifier()},
      User Text:  ${notification.getUserText()},
      Notification is clicked: ${String(isClicked)}.`,
      [
        {
          text: 'Dismiss',
          onPress: null,
        },
      ],
    );
  };

open = () => {
    UIManager.dispatchViewManagerCommand(
        findNodeHandle(this._storylyView),
        UIManager.getViewManagerConfig('STStoryly').Commands.open,
        [],
    );
};



  return (
    <>
      <SafeAreaView style={{ height: "100%", backgroundColor: "white"}}>
        <Animated.View
          style={{
            paddingTop: paddingTop,
            height: 130,
            backgroundColor: "white",
          }}
        >
          <Storyly
            ref={ref => { this.customStoryly = ref }}
            style={{
              height: "100%",
            }}
            storyGroupSize="custom"
            storyGroupIconWidth={80}
            storyGroupIconHeight={80}
            storyGroupIconCornerRadius={40}
            //storyInteractiveTextTypeface = "Montserrat-Italic"
            //storyItemTextTypeface = 'Montserrat-Italic'
            storyGroupTextSize={11}
            storyGroupListEdgePadding={25}
            storyGroupListPaddingBetweenItems={15}
            storylyId="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTE3NTIsImluc19pZCI6MTI1ODl9.Z_F9S6OyES9rfwYDFV47zT0_DbP0WUwqE5EmYOpCZRQ"
            onLoad={event => { console.log("[Storyly] onLoad", event.storyGroupList.length, event.dataSource); }}
            onFail={errorMessage => { console.log("[Storyly] onFail"); }}
            /*onPress={story => { 
              console.log("[Storyly] onPress", story.id); 
              navigation.navigate('Details');
              
            }}*/
            onPress={story => { Linking.openURL(story.media.actionUrl)
              console.log("[Storyly] onPress--->", story); 
              this.customStoryly.close()
            }}
            onEvent={eventPayload => { console.log("[Storyly] onEvent"); }}
            onStoryOpenFailed = {(onStoryOpenFailed) => console.log("openStoryFailed")}
            onStoryOpen={() => { console.log("[Storyly] onStoryOpen"); }}
            onStoryClose={() => { console.log("[Storyly] onStoryClose"); }}
            onUserInteracted={interactionEvent => { console.log("[Storyly] onStoryUserInteracted"); }}
          />
        </Animated.View>

        <View style={{alignItems: 'center', justifyContent: 'center', }}>
          <Button title={'Go to Next Page'}  onPress={() => navigation.navigate('Details')}/>
          
        </View>
        
      </SafeAreaView>
    </>
  );
}

function DetailsScreen() {
  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
      <Text>Details Screen</Text>
    </View>
  );
}

const Stack = createNativeStackNavigator();

const linking = {
  prefixes: ['peoplesapp://'],
  config: {
    initialRouteName: 'Home',
    screens: {
      Home: {
        path: 'home'
      },
      Details: {
        path: 'details'
      }
    }
  }
};

function App() {
  return (
    <NavigationContainer
      linking={linking}
      fallback={<ActivityIndicator color="blue" size="large" />}
    >
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} />
        <Stack.Screen name="Details" component={DetailPage} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

export default App;