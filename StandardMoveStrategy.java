public class StandardMoveStrategy implements IMoveStrategy {
    @Override
    public int movePlayer(Player player, IDiceService dice, Board board) {
        int diceRoll = dice.rollDice();
        int currentPosition = player.getCurrentPosition();
        int newPosition = currentPosition + diceRoll;

        if (newPosition > board.getSize()) {
            return currentPosition;
        }

        player.setCurrentPosition(newPosition);

        int finalPosition = board.getNewPositionAfterSnakeOrLadder(newPosition);
        player.setCurrentPosition(finalPosition);

        updateCellOccupancy(player, currentPosition, finalPosition, board);

        System.out.println(player.getName() + " rolled " + diceRoll + 
                          " and moved from " + currentPosition + " to " + finalPosition);

        return finalPosition;
    }

    private void updateCellOccupancy(Player player, int oldPosition, int newPosition, Board board) {
        if (oldPosition > 0) {
            Cell oldCell = board.getCell(oldPosition);
            if (oldCell != null) {
                oldCell.removePlayer(player);
            }
        }

        Cell newCell = board.getCell(newPosition);
        if (newCell != null) {
            newCell.addPlayer(player);
        }
    }
}
