<h1 align="center">tcg.binding</h1>

<div align="center">
  <strong>A client keybind library that doesn't suck</strong>
</div>
<br />
  
 ## Getting Started
 
The first thing you'll need to get started with tcg.binding is an instance of the `BindingSystem` class, which is parameterized with platform-specific representations of what a *key* and a *button* actually are in a given application, for performance purposes. This class acts as the core provider of **bindable** objects within the library ecosystem. To use an example of how it might be used in client modules:
 ```java
 class Module {
    private final Bindable<KeyboardKey, MouseButton> binds;
    
    private boolean running;
    
    Module(BindingSystem<KeyboardKey, MouseButton> bindSys) {
        this.binds = bindSys.bindableFor(this::toggle);
    }
    
    public Bindable<KeyboardKey, MouseButton> getBinding() {
        return this.binds;
    }
    
    void toggle() {
        this.running = !this.running;
    }
 }
 ```
 As you can see, we use the `BindingSystem` to get an instance of the `Bindable` class, which serves as the point of interaction between a *bindable* object and any callers interested in manipulating those binds in some way. An example can be seen in this contrived user command for adding keybinds to a module:
 ```java
 @Namespace("bind")
 class BindCommands {
    private final ModuleManager moduleManager;
    
    BindCommands(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }
    
    @Command("add")
    void addBindToModule(String moduleName, List<String> interactionNames) {
        Optional<Module> moduleOpt = this.moduleManager.findModuleByName(moduleName);
        Set<Interaction<KeyboardKey, MouseButton>> interactions = new HashSet<>();
        for (String name : interactionNames) {
            if (Keyboard.isKeyName(name)) interactions.add(new KeyboardKey(name));
            else if (Mouse.isButtonName(name)) interactions.add(new MouseButton(name));
            else throw new IllegalArgumentException("'" + name + "' is not a valid key or button name");
        }
        moduleOpt.ifPresent(module -> module.getBinding().bind(interactions));
    }
 }
 ```
 And just like that, you have keyboard + mouse binds!
